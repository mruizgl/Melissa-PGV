# Imagen base para MariaDB
FROM mariadb:10.5 as mariadb_base

# Configuración de MariaDB
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=exampledb
VOLUME /var/lib/mysql

# Imagen base para Tomcat
FROM tomcat:9.0 as tomcat_base

# Copiar una aplicación de ejemplo al directorio webapps de Tomcat
COPY ./sample.war /usr/local/tomcat/webapps/

# Imagen base para CloudBeaver
FROM dbeaver/cloudbeaver:latest as cloudbeaver_base

# Imagen final que integra los servicios
FROM ubuntu:20.04

# Instalar herramientas esenciales
RUN apt-get update && apt-get install -y \
    wget \
    curl \
    mysql-client \
    && apt-get clean

# Copiar configuraciones de MariaDB
COPY --from=mariadb_base / /mariadb/

# Copiar configuraciones de Tomcat
COPY --from=tomcat_base / /tomcat/

# Copiar configuraciones de CloudBeaver
COPY --from=cloudbeaver_base / /cloudbeaver/

# Exponer puertos
EXPOSE 8080 8081

# Comando para ejecutar todos los servicios
CMD bash -c "service mysql start && /usr/local/tomcat/bin/catalina.sh run & /opt/cloudbeaver/cloudbeaver & wait"
