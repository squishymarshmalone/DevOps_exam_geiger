

TODO: https://www.terraform.io/docs/providers/heroku/r/app_config_association.html 
(wont be necessary as the profile isnt local)

# for å kjøre influxdb på windows (cmd)
docker run --rm influxdb:1.0 influxd config > influxdb.conf
mkdir influxdb
docker run --name influxdb -p 8083:8083 -p 8086:8086 -p 25826:25826/udp  -v %cd%/influxdb:/var/lib/influxdb  -v %cd%/influxdb.conf:/etc/influxdb/influxdb.conf:ro  -v %cd%/types.db:/usr/share/collectd/types.db:ro  influxdb:1.0


Jeg har hentet mye inspirasjon på hvordan jeg skriver applikasjonen, struktur på API osv. fra materiell fra undvervisningen i Enterprise programmering 2
https://github.com/arcuri82/testing_security_development_enterprise_systems


- [ ] meningsfylte enhetstester
- [ ] meningsfylte logger

- [ ] bruk av alle metrics + 1: følgende type metrics gauge, counter, DistributionSummary, Timer, LongTaskTimer.


- [ ] SimpleMeterRegistry når applikasjonen kjører på heroku, i travis og når testene kjøres.
- [ ] Håndter om det skal logges når applikasjonen kjøres lokalt, og evt. under testene.



og bruk av minst følgende type metrics 

A gauge is a handle to get the current value. Typical examples for gauges would be the size of a collection or map or number of threads in a running state.

TIP
Gauges are useful for monitoring things with natural upper bounds. We don’t recommend using a gauge to monitor things like request count, as they can grow without bound for the duration of an application instance’s life.
TIP
Never gauge something you can count with a Counter!

- [X] gauge
- [X] counter  
//num requests
//num new devices


- [X] DistributionSummary 
- [X] Timer  ?
- [X] LongTaskTimer ?