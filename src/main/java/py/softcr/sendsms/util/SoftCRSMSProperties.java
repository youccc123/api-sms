package py.softcr.sendsms.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.Properties;

public class SoftCRSMSProperties implements Serializable {

        private static final long serialVersionUID = 1L;

        private static final Logger logger = LogManager.getLogger(SoftCRSMSProperties.class);

        private Properties properties;

        public SoftCRSMSProperties(){
            properties = new Properties();
            try {
                this.properties.load(this.getClass().getResourceAsStream("/softcrsms.properties"));
            } catch (Exception e) {

                logger.error("error leyendo softcrsms.properties", e);
            }
        }

        public String getProperty(String key) {
            String value = null;
            if (this.properties.containsKey(key)) {
                value = this.properties.getProperty(key);
            }

            return value;
        }
}
