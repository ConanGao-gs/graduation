package com.example.demo;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//代码生成器
public class CodeGenerator {
    //读取控制台数据
    public static String scanner(String tip){
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入"+tip+": ");
        System.out.println(help.toString());
        if(scanner.hasNext()){
            String ipt = scanner.next();
            if(StringUtils.isNotEmpty(ipt)){
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的"+tip+"! ");
    }

    public static void main(String[] args){
        // 代码生成器
        AutoGenerator mpg =new AutoGenerator();

        //region 1.全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        // 获取项目路径 当前项目路径为 D:\ideProject\20210119
        String projectPath = System.getProperty("user.dir");
        // 去掉方法名前缀I
        globalConfig.setControllerName("%sController");
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        // 生成文件的输出目录
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        // 设置作者
        globalConfig.setAuthor("高爽");
        // 是否覆盖文件 true则每次生成会将之前添加的业务代码删除
        globalConfig.setFileOverride(true);
        // 生成后是否打开文件
        globalConfig.setOpen(false);
        // 将全局配置添加到代码生成器中
        mpg.setGlobalConfig(globalConfig);
        //endregion

        //region 2.数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        // 设置数据类型  这里使用的是Mysql
        dataSourceConfig.setDbType(DbType.MYSQL);
        // 自定义数据类型转换  这里使用Mysql的数据转换器
        dataSourceConfig.setTypeConvert(new MySqlTypeConvert());
        // 驱动，URL，用户名和密码 这里是mysql 8
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/test9?characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("437406");
        // 将数据源配置添加到代码生成器中
        mpg.setDataSource(dataSourceConfig);
        //endregion

        //region 3.包配置
        PackageConfig packageConfig = new PackageConfig();
        // 生成的代码在哪个模块名下 如果指定为 test，则生成的xml会在 mapper/test/ 目录下
        packageConfig.setModuleName(scanner("模块名"));
        // 父包名 如果为空子包名必须写全部， 否则就只需写子包名
        packageConfig.setParent("com.example.demo");
        //设置controller,service,entity包名
        packageConfig.setEntity("entity");
        packageConfig.setController("controller");
        packageConfig.setMapper("mapper");
        // 将包配置添加到代码生成器中
        mpg.setPackageInfo(packageConfig);
        //endregion

        //region 4.自定义配置
        InjectionConfig injectionConfig = new InjectionConfig(){
            @Override
            public void initMap() {
                // to do something
            }
        };
        // 自定义输出配置 会被优先输出
        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + packageConfig.getModuleName()
                        + "/"+tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        //将自定义输出配置添加到自定义配置上
        injectionConfig.setFileOutConfigList(focList);
        //将自定义配置添加到代码生成器中
        mpg.setCfg(injectionConfig);
        //endregion

        //region 5.策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        // 设置命名格式
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setInclude(scanner("表名,多个英文逗号分割").split(","));
        // 实体是否为lombok模型（默认为false）
        strategyConfig.setEntityLombokModel(true);
        // 生成@RestController控制器
        strategyConfig.setRestControllerStyle(true);
        // 设置自定义继承的Entity类全称，带包名
        // strategyConfig.setSuperEntityClass("com.example.demo.BaseEntity");
        // 设置自定义继承的Controller类全称，带包名
        // strategyConfig.setSuperControllerClass("com.example.demo.BaseController");
        // 设置自定义基础的Entity类，公共字段
        strategyConfig.setSuperEntityColumns("id");
        // 驼峰转连字符
        strategyConfig.setControllerMappingHyphenStyle(true);
        //去掉表名前缀
        strategyConfig.setTablePrefix("gs_");
        //将策略配置添加到代码生成器中
        mpg.setStrategy(strategyConfig);
        //endregion

        //region 6.模板引擎
        //模板引擎 这里使用freemarker
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        //endregion

        //region 7.模板引擎
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        //不生成默认的在mapper下的xml文件，这里是在自定义配置里将xml文件生成到resources下
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);
        //endregion

        //执行代码生成器
        mpg.execute();
    }
}
