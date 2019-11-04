

# Kjøre lokalt
Sett logz.io env variabler
LOGZ_TOKEN=******;LOGZ_URL=https://listener.logz.io:8071


dersom man ønsker å ta i mot metrics med influx, start influx docker container og sett spring profile til "local" 

#### for å kjøre influxdb på windows (cmd)
docker run --rm influxdb:1.0 influxd config > influxdb.conf
mkdir influxdb
docker run --name influxdb -p 8083:8083 -p 8086:8086 -p 25826:25826/udp  -v %cd%/influxdb:/var/lib/influxdb  -v %cd%/influxdb.conf:/etc/influxdb/influxdb.conf:ro  -v %cd%/types.db:/usr/share/collectd/types.db:ro  influxdb:1.0


#### for mac ligger instruksjoner i labguide



Env variabler som må settes i dette repoet.
travis encrypt DOCKER_USERNAME=<docker_username> --add  
travis encrypt DOCKER_PASSWORD=<docker_password> --add  

travis encrypt HEROKU_EMAIL=<heroku_email> --add   
travis encrypt HEROKU_API_KEY=<heroku_api_key> --add    


Jeg har hentet mye inspirasjon på hvordan jeg skriver applikasjonen, struktur på API osv. fra materiell fra undvervisningen i Enterprise programmering 2
https://github.com/arcuri82/testing_security_development_enterprise_systems

