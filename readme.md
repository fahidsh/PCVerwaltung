# PC Verwaltungsystem

---
Der Software ladet die Daten aus Datenbank und zeigt dieses auf dem Java Swing Gui mit Navigationsmöglichkeit durch Buttons un ScrollBar


## Datenabholung am Start

---
In der Anfangsversion, ladet der Software die Daten aus der Datenbank nur am Start.

---
## Runtime Datenabholung
am Programstart werden nur die IDs aus Datenbank in einer Integer Liste geladen
erst in der aktualisiereOberflaeche() methode werden die Daten aus der Datenbank, nur für betroffene ID geladen
jede Minute wird die ID-Liste aktualisiert