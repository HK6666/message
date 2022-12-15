package com.hk.message.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.IFill;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

/**
 * Created by huangkai on 2022/12/11.
 */
public class codeGenerator {
    public static void main(String[] args) {

        // 数据源配置
        DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig
                .Builder(
                "jdbc:mysql://localhost:3306/message" +
                        "",
                "root",
                "hk19990305");

        // 全局配置
        GlobalConfig.Builder globalConfigBuilder = new GlobalConfig.Builder();
        // 代码生成目录
        String projectPath = System.getProperty("user.dir");
        globalConfigBuilder.outputDir(projectPath + "/src/main/java");
        // 作者
        globalConfigBuilder.author("hk");
        // 结束时是否打开文件夹
        globalConfigBuilder.disableOpenDir();
        // 是否覆盖旧的文件
//        globalConfigBuilder.fileOverride();
        // 实体属性Swagger2注解
        globalConfigBuilder.enableSwagger();

        // 包配置，如模块名、实体、mapper、service、controller等
        PackageConfig.Builder packageConfigBuilder = new PackageConfig.Builder();
        packageConfigBuilder.moduleName("dao");
        packageConfigBuilder.parent("com.hk.message");
        packageConfigBuilder.entity("entity");
        packageConfigBuilder.mapper("mapper");

//        packageConfigBuilder.service("service");
        //packageConfigBuilder.serviceImpl("");
//        packageConfigBuilder.controller("controller");
        packageConfigBuilder.pathInfo(Collections.singletonMap(OutputFile.xml,System.getProperty("user.dir")+"/src/main/resources/mapper"));


        // ------------------------------ 策略配置 Start
        StrategyConfig.Builder strategyConfigBuilder = new StrategyConfig.Builder();
        // 设置需要映射的表名
        strategyConfigBuilder.addInclude(
                "test"
                // , "t_anniversary"
                // , "t_gallery"
                // , "t_share"
        );
        // 去除前缀"t_"
//        strategyConfigBuilder.addTablePrefix("t_");

        // -------------------- Entity 策略配置
        // 主键生成策略
        // strategyConfigBuilder.entityBuilder().idType(IdType.INPUT);
        // 下划线转驼峰
        strategyConfigBuilder.entityBuilder().naming(NamingStrategy.underline_to_camel);
        strategyConfigBuilder.entityBuilder().columnNaming(NamingStrategy.underline_to_camel);
        // 开启链式模型
        strategyConfigBuilder.entityBuilder().enableChainModel();
        // 开启Lombok模型
        strategyConfigBuilder.entityBuilder().enableLombok();
        // Boolean 类型字段移除 is 前缀
        // strategyConfigBuilder.entityBuilder().enableRemoveIsPrefix();
        // 文件覆盖
        strategyConfigBuilder.entityBuilder().enableFileOverride();
        strategyConfigBuilder.mapperBuilder().enableFileOverride();
        // 开启生成实体时生成字段注解
        strategyConfigBuilder.entityBuilder().enableTableFieldAnnotation();
        // 逻辑删除
        strategyConfigBuilder.entityBuilder().logicDeleteColumnName("deleted");
        strategyConfigBuilder.entityBuilder().logicDeletePropertyName("deleted");
        // 乐观锁
        strategyConfigBuilder.entityBuilder().versionColumnName("version");
        strategyConfigBuilder.entityBuilder().versionPropertyName("version");
        // 自动填充
        IFill createTime = new Column("create_time", FieldFill.INSERT);
        IFill updateTime = new Column("update_time", FieldFill.INSERT_UPDATE);
        strategyConfigBuilder.entityBuilder().addTableFills(createTime, updateTime);
        // -------------------- Controller 策略配置
        // 设置父类
        // strategyConfigBuilder.controllerBuilder().superClass(Class<BaseController.class>);
        // strategyConfigBuilder.controllerBuilder().superClass("com.baomidou.global.BaseController");
        // 开启生成@RestController 控制器
        // strategyConfigBuilder.controllerBuilder().enableRestStyle();
        // 开启驼峰转连字符

        strategyConfigBuilder.controllerBuilder().enableHyphenStyle();
        strategyConfigBuilder.entityBuilder().convertFileName((entityName -> entityName+"Model"));
        // -------------------- Service 策略配置
        // 设置 IService 父类
        strategyConfigBuilder.serviceBuilder().superServiceClass(IService.class);
        // strategyConfigBuilder.serviceBuilder().superServiceClass("com.baomidou.global.BaseService");
        // 设置 ServiceImpl 父类
//        strategyConfigBuilder.serviceBuilder().superServiceImplClass(ServiceImpl.class);
        // strategyConfigBuilder.serviceBuilder().superServiceImplClass("com.baomidou.global.BaseService");
        // -------------------- Mapper 策略配置
        // 设置父类
        strategyConfigBuilder.mapperBuilder().superClass(BaseMapper.class);
        // strategyConfigBuilder.mapperBuilder().superClass("com.baomidou.global.BaseMapper");
        // 开启 @Mapper 注解
        strategyConfigBuilder.mapperBuilder().enableMapperAnnotation();
        // 启用 BaseResultMap 生成
        strategyConfigBuilder.mapperBuilder().enableBaseResultMap();
        // 启用 BaseColumnList
        strategyConfigBuilder.mapperBuilder().enableBaseColumnList();
        // 设置缓存实现类
        // strategyConfigBuilder.mapperBuilder().cache(MyMapperCache.class);
        // ------------------------------ 策略配置 End

//        // 自定义配置
//        InjectionConfig.Builder injectionConfigBuilder = new InjectionConfig.Builder();
//
        // 模板引擎配置
//        TemplateConfig.Builder templateConfigBuilder = new TemplateConfig.Builder();
//        // 如果模板引擎是 freemarker
//        //String templatePath = "/templates/mapper.xml.ftl";
//        // 如果模板引擎是 velocity
//        String templatePath = "/templates/mapper.xml.vm";
//        templateConfigBuilder.entity(templatePath);

        //禁用某些文件的生成
        TemplateConfig.Builder templateConfigBuilder = new TemplateConfig.Builder();
        templateConfigBuilder.disable(TemplateType.SERVICE);
        templateConfigBuilder.disable(TemplateType.SERVICE_IMPL);
        templateConfigBuilder.disable(TemplateType.CONTROLLER);
        // 创建代码生成器对象，加载配置
        AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfigBuilder.build());
        autoGenerator.global(globalConfigBuilder.build());
        autoGenerator.packageInfo(packageConfigBuilder.build());
        autoGenerator.strategy(strategyConfigBuilder.build());
        //autoGenerator.injection(injectionConfigBuilder.build());
        autoGenerator.template(templateConfigBuilder.build());

        // 执行
        autoGenerator.execute();
    }
}
