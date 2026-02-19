# IslandHide

Plugin Minecraft per Paper 1.21+ che nasconde comandi e informazioni sensibili del server.

## Requisiti
- Java 21
- Paper 1.21+
- ProtocolLib 5.4.0

## Build
```bash
gradle clean shadowJar
```

Output:
```
build/libs/IslandHide-1.0.0-SNAPSHOT.jar
```

## Installazione
1. Copia il JAR in `plugins/`
2. Avvia il server
3. Modifica `plugins/IslandHide/config.yml`
4. Riavvia o ricarica

## Configurazione
File: `config.yml`

Sezioni principali:
- `hiding.commands`: blocco comandi con whitelist/blacklist e regex
- `hiding.plugins`: blocco tab-completion e comandi plugin
- `hiding.brand`: mascheramento brand
- `hiding.server-list`: MOTD, version e player sample
- `security`: blocco canali client-mod

## Permessi
- `islandhide.admin`: bypass totale e gestione
- `islandhide.bypass.commands`: bypass blocco comandi
- `islandhide.bypass.plugins`: bypass blocco plugin

## API
La API pubblica è disponibile in [IslandHideAPI](file:///c:/Users/azzol/Downloads/Plugins/IslandHide/src/main/java/net/solryn/islandhide/api/IslandHideAPI.java).

Vedi anche `docs/API.md`.
