## 简单的实现斯诺克英文网

## 部署步骤

> 以下是linux环境的部署

1. 安装并启动docker
2. 安装git（如果没有）
3. 下载环境依赖包(docker-develop-env)
```shell script
git clone git@github.com:fulln/docker-develop-env.git
```
4. 将本项目从github上下载到服务器

```shell script
git clone git@github.com:fulln/ftz.git
```

- 将本项目中`docker-compose.yml`替换掉环境依赖包中的`docker-compose.yml`
- 将本项目中`sql/init.sql` 替换掉环境依赖包中的`mysql/sql/init.sql`

5. 管理员执行脚本 
 
进入 docker-develop-env 文件下并管理员执行 `run.sh`
