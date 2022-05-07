create table news_category
(
    id            int auto_increment comment '主键',
    create_by     varchar(32)  null comment '创建人',
    create_time   datetime     null comment '创建时间',
    update_by     varchar(32)  null comment '更新人',
    update_time   int          null comment '更新时间',
    category_name varchar(200) null comment '分类名称',
    sort          int          null comment '分类排序',
    category_code varchar(32)  null comment '分类代码',
    constraint news_category_pk
        primary key (id)
) comment '新闻分类';
create table news_tag
(
    id          int auto_increment comment '主键',
    create_by   varchar(32)  null comment '创建人',
    create_time datetime     null comment '创建时间',
    update_by   varchar(32)  null comment '更新人',
    update_time datetime     null comment '更新时间',
    tag_name    varchar(200) null comment '标签名称',
    tag_code    varchar(32)  null comment '标签编码',
    constraint news_tag_pk
        primary key (id)
) comment '新闻标签';
create table news_link
(
    id          int auto_increment comment '主键',
    create_by   varchar(32)  null comment '创建人',
    create_time datetime     null comment '创建时间',
    update_by   varchar(32)  null comment '更新人',
    update_time datetime     null comment '更新时间',
    link_name   varchar(500) null comment '链接名称',
    link_url    varchar(500) null comment '链接地址',
    constraint news_link_pk
        primary key (id)
) comment '友情链接';

create table news_article
(
    id          int auto_increment comment '主键',
    create_by   varchar(32)   null comment '创建人',
    create_time datetime      null comment '创建时间',
    update_by   varchar(32)   null comment '更新人',
    update_time datetime      null comment '更新时间',
    title       varchar(500)  null comment '标题',
    excerpt     varchar(2000) null comment '摘要',
    category    int           null comment '新闻分类ID',
    img         varchar(200)  null comment '封面图片',
    content     text          null comment '正文',
    author      int           null comment '作者',
    views       int           null comment '阅读量',
    constraint news_article_pk
        primary key (id)
) comment '新闻';
create table news_comment
(
    id          int auto_increment comment '主键',
    create_by   varchar(32) null comment '创建人',
    create_time datetime    null comment '创建时间',
    update_by   varchar(32) null comment '更新人',
    update_time datetime    null comment '更新时间',
    news_id     int         null comment '新闻ID',
    content     text        null comment '评论内容',
    constraint news_comment_pk
        primary key (id)
) comment '新闻评论';
