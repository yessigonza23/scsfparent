FROM quay.io/keycloak/keycloak:latest as builder

# Enable health and metrics support
ENV KC_HEALTH_ENABLED=true
ENV KC_METRICS_ENABLED=true

# Configure a database vendor
ENV KC_DB=postgres

# The username of the database user
ENV KC_DB_USERNAME=postgres

# The password of the database user
ENV KC_DB_PASSWORD=p0st2021.

# The full database JDBC URL. If not provided, a default URL is set based on the selected database vendor.
ENV KC_DB_URL=jdbc:postgresql://10.41.2.2:5432/control

# Hostname for the Keycloak server.
# ENV KC_HOSTNAME=localhost

WORKDIR /opt/keycloak
# for demonstration purposes only, please make sure to use proper certificates in production instead
# RUN keytool -genkeypair -storepass password -storetype PKCS12 -keyalg RSA -keysize 2048 -dname "CN=server" -alias server -ext "SAN:c=DNS:localhost,IP:127.0.0.1" -keystore conf/server.keystore
RUN /opt/keycloak/bin/kc.sh build

FROM quay.io/keycloak/keycloak:latest
COPY --from=builder /opt/keycloak/ /opt/keycloak/

ENTRYPOINT ["/opt/keycloak/bin/kc.sh"]