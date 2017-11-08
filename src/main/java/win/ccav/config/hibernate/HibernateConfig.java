package win.ccav.config.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by paul on 2017/4/6.
 */
@Repository

public class HibernateConfig {
    @Autowired
    private DataSource dataSource;
    @Value("spring.jpa.properties.hibernate.dialect")
    private String dialect;
    @Value("showSql")
    private String showSql;
    @Value("formatSql")
    private String formatSql;
    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(DataSource dataSource){

        //// TODO: 2017/3/30 这里有疑问，就是我在我这个DataSource的创建方式对吗？
        //你还可以使用 annotatedClasses 属性来将应用程序中所有的持久化
        //类以全限定名的方式明确列出,这个的意思是不是用这个方法来将我需要做映射的类来直接说明
        /*
        * localSessionFactoryBean.setAnnotatedClasses(new Class<?>[] {mode.class....})
        *
        *
        * */
        LocalSessionFactoryBean localSessionFactoryBean=new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setPackagesToScan(new String[]{"win.ccav.model"});
        Properties props = new Properties();
        props.setProperty("dialect", dialect);
        props.setProperty("show_sql",showSql);
        props.setProperty("format_sql",formatSql);
        //props.setProperty("characterEncoding","utf-8");
        localSessionFactoryBean.setHibernateProperties(props);
        return localSessionFactoryBean;
    }
}
