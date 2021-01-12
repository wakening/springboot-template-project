# template-project

springboot工程模板，预配置，快速完成多数据源、配置中心、服务化等构建，包含打包启动脚本

```xml
<modules>
    <module>project-dao</module>
    <module>project-service</module>
    <module>project-client</module>
</modules>
```
调用链: rpc server: project-client --> rpc server: project-service --> sdk: project-dao