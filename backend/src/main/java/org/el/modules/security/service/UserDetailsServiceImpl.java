/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.el.modules.security.service;

import lombok.RequiredArgsConstructor;
import org.el.exception.BadRequestException;
import org.el.exception.EntityNotFoundException;
import org.el.modules.security.config.bean.LoginProperties;
import org.el.modules.security.service.dto.JwtUserDto;
import org.el.modules.system.service.DataService;
import org.el.modules.system.service.RoleService;
import org.el.modules.system.service.UserService;
import org.el.modules.system.service.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Zheng Jie
 * @date 2018-11-22
 */
@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    private final RoleService roleService;
    private final DataService dataService;
    private final LoginProperties loginProperties;

    private final UserCacheManager USER_DTO_CACHE;

    public void setEnableCache(boolean enableCache) {
        this.loginProperties.setCacheEnable(enableCache);
    }

    public static ExecutorService executor = newThreadPool();

    @Override
    public JwtUserDto loadUserByUsername(String username) {
        JwtUserDto jwtUserDto = null;
        Future<JwtUserDto> future = USER_DTO_CACHE.get(username);
        if (!loginProperties.isCacheEnable()) {
            UserDto user;
            try {
                user = userService.findByName(username);
            } catch (EntityNotFoundException e) {
                // SpringSecurity会自动转换UsernameNotFoundException为BadCredentialsException
                throw new UsernameNotFoundException(username, e);
            }
            if (user == null) {
                throw new UsernameNotFoundException("");
            } else {
                if (!user.getEnabled()) {
                    throw new BadRequestException("账号未激活！");
                }
                jwtUserDto = new JwtUserDto(
                        user,
                        dataService.getDeptIds(user),
                        roleService.mapToGrantedAuthorities(user)
                );
            }
            return jwtUserDto;
        }

        if (future == null) {
            Callable<JwtUserDto> call = () -> getJwtBySearchDb(username);
            FutureTask<JwtUserDto> ft = new FutureTask<>(call);
            future = USER_DTO_CACHE.putIfAbsent(username, ft);
            if (future == null) {
                future = ft;
                executor.submit(ft);
            }
            try {
                return future.get();
            } catch (CancellationException e) {
                USER_DTO_CACHE.remove(username);
                System.out.println("error" + Thread.currentThread().getName());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e.getMessage());
            }
        } else {
            try {
                jwtUserDto = future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e.getMessage());
            }
            // 检查dataScope是否修改
            List<Long> dataScopes = jwtUserDto.getDataScopes();
            dataScopes.clear();
            dataScopes.addAll(dataService.getDeptIds(jwtUserDto.getUser()));

        }
        return jwtUserDto;

    }

    private JwtUserDto getJwtBySearchDb(String username) {
        UserDto user;
        try {
            user = userService.findByName(username);
        } catch (EntityNotFoundException e) {
            // SpringSecurity会自动转换UsernameNotFoundException为BadCredentialsException
            throw new UsernameNotFoundException("", e);
        }
        if (user == null) {
            throw new UsernameNotFoundException("");
        } else {
            if (!user.getEnabled()) {
                throw new BadRequestException("账号未激活！");
            }
            return new JwtUserDto(
                    user,
                    dataService.getDeptIds(user),
                    roleService.mapToGrantedAuthorities(user)
            );
        }

    }

    public static ExecutorService newThreadPool() {
        ThreadFactory namedThreadFactory = new ThreadFactory() {
            final AtomicInteger sequence = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                int seq = this.sequence.getAndIncrement();
                thread.setName("future-task-thread" + (seq > 1 ? "-" + seq : ""));
                if (!thread.isDaemon()) {
                    thread.setDaemon(true);
                }

                return thread;
            }
        };
        return new ThreadPoolExecutor(10, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());
    }
}
