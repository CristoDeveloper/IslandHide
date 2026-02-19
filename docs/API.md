# IslandHide API

## Overview
Entry point: `net.solryn.islandhide.api.IslandHideAPI`

## Metodi

### isCommandHidden
Firma:
```
boolean isCommandHidden(String command)
```

Descrizione:
Ritorna `true` se il comando risulta bloccato dalla configurazione attiva.

Esempio:
```java
boolean hidden = IslandHideAPI.isCommandHidden("/plugins");
```

### reload
Firma:
```
void reload()
```

Descrizione:
Ricarica il file `config.yml`.

Esempio:
```java
IslandHideAPI.reload();
```

## Note
- La API è statica e richiede che il plugin sia già stato abilitato.
- Se il plugin non è caricato, i metodi non eseguono azioni.
