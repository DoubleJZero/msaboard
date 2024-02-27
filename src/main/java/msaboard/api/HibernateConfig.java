package msaboard.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * HibernateConfig
 *
 * <pre>
 * code history (Record changes as needed)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
@Configuration
public class HibernateConfig {

    @Value("${spring.jpa.database-platform}")
    private String databasePlatform;

    @Bean
    JpaVendorAdapter jpaVendorAdapter() {
        // 해당 설정 안하면 SequenceGenerator 에서 자동생성되는 쿼리 오류나서 정상작동 안됨.
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabasePlatform(databasePlatform);
        return hibernateJpaVendorAdapter;
    }
}
