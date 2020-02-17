module org.openjfx.mavenfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
	requires org.hibernate.orm.core;
	requires java.persistence;
	requires java.logging;
	requires java.sql;
	requires java.xml.bind;
	requires json.simple;


	
    opens org.openjfx.mavenfx to org.hibernate.orm.core;
    opens org.openjfx.mavenfx.session to org.hibernate.orm.core;
    opens org.openjfx.mavenfx.user to org.hibernate.orm.core;
    opens org.openjfx.mavenfx.trades to org.hibernate.orm.core, javafx.base;
 
    exports org.openjfx.mavenfx;
  
}