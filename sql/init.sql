
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'qq1203943228' WITH GRANT OPTION;

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`company` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

use company;
-- auto-generated definition
create table season_player
(
    id                  bigint(10) unsigned auto_increment comment '主键'
        primary key,
    type                int                                  not null comment '用户id',
    first_name          varchar(32)                          null comment '名',
    middle_name         varchar(32)                          null comment '名',
    last_name           varchar(32)                          null comment '姓',
    team_name           varchar(32)                          null comment '球队名称',
    team_number         int                                  null comment '球队编号',
    team_season         int                                  null comment '球队赛季',
    short_name          varchar(32)                          null comment '简称',
    nationality         varchar(32)                          null comment '国籍',
    sex                 char(16)                             null comment '性别',
    bio_page            varchar(128)                         null comment '主页',
    born                varchar(32)                          null comment '生日',
    twitter             varchar(32)                          null comment '推特',
    Surname_first       tinyint(1) default 0                 null comment '姓名开始',
    License             varchar(32)                          null comment '驾照',
    club                varchar(32)                          null comment '俱乐部',
    URL                 varchar(32)                          null comment '地址',
    photo               varchar(128)                         null comment '照片',
    photo_source        varchar(32)                          null comment '照片来源',
    first_season_as_pro int                                  null comment '第一赛季',
    last_season_as_pro  int        default 0                 null comment '最后一季',
    create_sys_tm       datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_sys_tm       datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    delete_flag         tinyint    default 0                 not null comment '逻辑删除标记:0未删除,1已删除',
    translate_id        int        default 0                 null comment '对应翻译的那条'
)
    comment '赛季球员表' charset = utf8mb4;


-- auto-generated definition
create table player_rankings
(
    id            int unsigned auto_increment comment '主键'
        primary key,
    create_sys_tm datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_sys_tm datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    delete_flag   tinyint  default 0                 not null comment '逻辑删除标记:0未删除,1已删除',
    position      int unsigned                       not null comment '排名',
    player_id     int unsigned                       not null comment '球员id',
    season        varchar(64)                        not null comment '赛季',
    sum           int unsigned                       not null comment '身价',
    type          varchar(64)                        not null comment '排名类型'
)
    comment '足球球员排行' charset = utf8mb4;

