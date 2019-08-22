# barsim
Nine Kick Off Bar Simulator

Opstart af Spring boot applikation

git clone git@github.com:turnipsoft/barsim.git

gradle bootRun

applikationen : http://localhost:9999
prometheus endpoint : http://localhost:9999/actuator/prometheus


Enabling af micrometer prometheus i Spring boot applikation

build.gradle:
implementation 'io.micrometer:micrometer-registry-prometheus:latest.release'

application.yml:
management:
  endpoints:
    web:
      exposure:
        include: info,health,prometheus

http://localhost:9999/actuator/prometheus



Lav target i prometheus docker:

docker exec -it prometheus sh
vi /etc/prometheus/prometheus.yml

  - job_name: 'barsim'                                                                                                         
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['host-ip:9999']

exit

docker restart prometheus


Tilføj Pie-chart plugin til Grafana:
docker exec –it grafana /bin/bash
grafana-cli plugins install grafana-piechart-panel
docker restart grafana
