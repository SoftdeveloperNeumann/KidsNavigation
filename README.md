# KidsNavigation App

## Übersicht über die Navigation
Die Navigationskomponente besteht aus drei Schlüsselteilen, die harmonisch zusammenarbeiten. Sie sind:

1. Navigationsdiagramm (Neue XML-Ressource) – Dies ist eine Ressource, die alle navigationsbezogenen Informationen an einem zentralen Ort enthält. Dazu gehören alle Orte in Ihrer App, sogenannte Ziele , und mögliche Pfade, die ein Benutzer durch Ihre App nehmen könnte.
2. NavHostFragment (Layout-XML-Ansicht) – Dies ist ein spezielles Widget, das Sie Ihrem Layout hinzufügen. Es zeigt verschiedene Ziele aus Ihrem Navigationsdiagramm an.
3. NavController (Kotlin/Java-Objekt) – Dies ist ein Objekt, das die aktuelle Position innerhalb des Navigationsdiagramms verfolgt. Es orchestriert den Austausch von Zielinhalten, während NavHostFragmentSie sich durch ein Navigationsdiagramm bewegen.
Wenn Sie navigieren, verwenden Sie das NavController-Objekt und teilen ihm mit, wohin Sie gehen möchten oder welchen Pfad Sie in Ihrem Navigationsdiagramm einschlagen möchten. Anschließend wird NavControllerdas entsprechende Ziel im NavHostFragment angezeigt.
