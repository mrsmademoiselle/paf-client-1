# Logtagebuch

## Sprint: ?
 
Datum: ? <br> Von: ?

--- 

## Sprint KW 2

### Historie

Einfache Historie Darstellung

### Matchinfo

Es wird aus den Nachrichten die wir bekommen immer das Bild herausgezogen. Begruendung: Wir bekommen die Nachricht so
oder so im vollem Umfang und koennen daher so oder so das gesamte UI auch aktualisierung. Wir haetten kein Vorteil das
initial zu setzen, zumal die initiale Prueufng des Gamestarts nun raus aus dem Controller ist.

### Lobby

Lobby wieder aktiv. Auslagen der Pruefung des Gamestarts.

---

## Sprint: 1

Datum: 11.12.-23.12. <br> Von: Franzi

Ich hab versucht es kurzzuhalten, aber ich wollte die wichtigen Sachen dokumentieren :(

### Package Struktur

Unsere Packagestruktur war generell sehr ungleichmäßig. Zum Einen hatten wir das Problem, dass bei uns alles
"Controller" hieß, und somit das Wort mehrere Bedeutungen hatte. Das habe ich durch Renaming gelöst ("Manager", oder
"Service" - über die Benennung kann man nochmal reden. Es ging mir nur darum, die Mehrdeutigkeit aufzulösen.) Alle
unsere "ComponentController" heißen nun weiterhin "Controller". Alle ComponentController die es benötigen, werden nach
diesem Modell einen ComponentService bekommen (z.B. UserService). Alle Weiteren "Service"-Klassen habe ich in /helper/
gesteckt, z.B. den TokenManager und den HttpConnector. Das sind im Prinzip Basis-Komponenten unseres Systems, die keine
direkten Services bei uns darstellen, aber eine ähnliche Aufgabe übernehmen.

Des Weiteren haben unsere Controller alle möglichen Aufgaben übernommen, die so anfallen - Datenbeschaffung,
Datenverarbeitung, Redirects, Datenausgabe, Styling. Um das Ganze etwas zu abstrahieren und auseinanderzuziehen, habe
ich eine neue Schicht eingeführt, wie wir sie auf dem Server haben - die Services. Diese sind nun größtenteils als
Vermittler zwischen "Repository" (Server) und Controller zuständig und übernehmen somit einen Großteil der
Informationsverarbeitungsaufgaben.

Es ist möglich, dass dadurch das MVC Modell nicht mehr 1:1 bei uns so umgesetzt wird, weil eine höhere Abstraktion bei
uns stattfindet. Andererseits kann man jedoch auch argumentieren, dass das MVC nach wie vor vorhanden sind, weil alle 3
Komponenten noch vorhanden sind und die Aufgaben übernehmen, die ihnen zustehen.

### UserAuthDto

Damit wir nicht zwei UserDtos parallel haben (eins mit Passwort/Username, eins mit Passwort/Username/Profilbild), habe
ich die beiden gemerged.

### general.css

Es gibt eine neue Datei "general.css", in denen alles an Styling rein kann, das auf mehreren Seiten wiederverwendet wird
oder für "Corporal identity" zuständig ist - z.B. Styling von buttons, textfeldern und fonts. Ich glaube, in der letzten
Iteration wurde derselbe Ansatz durch "main.css" verfolgt. Dort habe ich aber nur Code gefunden, der nur in einer
Komponente verwendet wurde (login.fxml) und nicht Code, der für mehrere Komponenten gilt. Vielleicht war das ein Feature
in Arbeit oder so. In jedem Fall ist das jetzt die general.css.

### PapaController

Layout Management ist ein Pain in JavaFX. Dadurch ist es aktuell auch ein Pain, Elemente anzuordnen, weil man alles
Pixelgenau machen muss und in den meisten Fällen dennoch ein Margin/Padding irgendwo hat wo man sich denkt: "Wo kommt
das her?!?!?!"

Das macht es aktuell krebsig, Seiten zu erstellen. Um das etwas leichter zu machen, habe ich mich davon abgewandt,
einfach überall statische Zahlen für das Layout zu verwenden (z.B. prefWidth="1700.00"). Ein Ansatz, das ganze
statischer ohne viel Aufwand statischer zu gestalten ist, dafür Variablen zu entwerfen, die die visualBounds des
Fensters darstellen und im FXML verwendet werden können. Diese Variablen sind im sogenannten "PapaController" (Name to
be fixed) über getter festgelegt. Stand heute extended alle unsere ComponentController diesen PapaController, um die
Variablen verwenden zu können.

Dadurch kann man statt prefWidth="1700.00" einfach prefWidth="${controller.width}" verwenden. Außerdem lassen sich die
Rows (HBoxes) jetzt in gleichmäßige Columns (VBoxes) aufteilen, indem man einfach die Width der "Column Container" auf
${controller.width / anzahlColumns} setzt.

Wir haben also dadurch die Möglichkeit, auch mit JavaFX die Seiten halbwegs responsive darzustellen. Somit bleiben
beispielsweise die Elemente auch beim Resizing da wo sie sind und müssen nicht händisch um x Pixel verschoben werden.

### FileManager

FileManager übernimmt datei-relevante Aufgaben, z.B. das Managen von FileChoosers und das heraussuchen von Bildern als
Ressourcen. Diese Aufgaben wurden in mehreren ComponentControllern verwendet und 1:1 stumpf kopiert, weswegen ich ein
Auslagern sinnvoll fand.

# Sprint: 2

Datum: 27.12.-31.12. <br> Von: Chris

### Websocket

Bisher verwendet der Client eine simple SocketConnection, welche nicht das Websocket-Protokoll implementiert. Daher wäre
so auch keine Verbindung zum WebSocketServer möglich. Dafür wurde die "org.java-websocket" - Dependency hinzugefügt (
also einmal mvn clean install ausführen). Die Klasse SocketConnector heißt nun WebSocketConnection um das Protokoll zu
verdeutlichen.

Die Klasse GameService ist nun ein Singleton. Zudem haben sich die Methoden verändert. Es gibt nun eine Methode "
lookForGame", welche eine WebSocketConnection in einem neuen Thread erstellt und den Ladebildschirm läd (loadLobby).
später können wir dann in der Connection abfangen wenn ein Match gefunden wurde und die GameView laden. Ebenfalls
implementiert sind eine stop-methode und eine stopLookingForGames-methode. Der Unterschied der beiden liegt darin dass
stop nur die Connection schließt und den Thread beendet, stopLookingForGames ruft stop auf und läd außerdem die
HomeView (Profil).

Die GameController Klasse kann nun ein Spielfeld mit CARD_X * CARD_Y Karten rendern. Es existieren ebenfalls Methoden um
Score und "turn" zu updaten.

### Bug-Ladeanimation

Immer wenn JavaFX bezogene Dinge in einem Thread passieren, der NICHT der JavaFX com.example.javafx.Main Thread ist, kommt es zu einer
Exception. Das Liegt daran, dass der Hauptthread, der den JavaFX Kontext hat, nicht ueber die Aenderungen informiert
wird. das bedeutet, dass wenn in einem Childthread irgendwelche Scene wechsel passieren sollen, der Mainthread darueber
informiert werden muss. Hierzu gibt es zwei Methoden:

1. Den Wechsel der Scene, Rerender etc. in Mainthread ausfuerhren. Dazu muss aber ein Event oder die Information dorch
   hochbubblen.
2. Die gewuenschte Funktion SPAETER ueber den Mainthread ausfuehren lassen. Dazu gibt es in javafx.application.Plattform
   die runLater() Methode die Methode Queued die Gewunschte Funktion und lasst sie im Mainthread mit dem JavaFX Kontext
   laufen, sobald der Mainthread dafuer zeit hat.
3.

### Nachrichtenuebermittling - Kommunikation vom WS zum Controller

Damit wir aenderungen im Controller durchnehmen koennen muss er, entsprechend den Nachrichten aus dem WS, auktualisiert
werde. Dazu wurde der Gameservice erweitert. Er hat nun ein Attribut fuer den GameController, in dem die GameController
instanz, ist die gerade gerendert ist Im GameController wird in der initialize() der GameService reingeholt und dort
direkt die aktuelle Instanz des GameControllers ubergeben Im WebSocketConnection kann dann uber das Feld im GameService
auf die Instanz des GameControllers zugegriffen werden Dadurch koennen wir nun die GameController view aktualisieren und
uber den GameService mit dem Socket reden

### UI Update ueber GameDTO

Eine Idee das UI ueber z.B. Databinding zu aktualiesieren ist es, das gameDto aus dem WS in den Controller zu bekommen
Dazu wurde der GameService erweitert. Dieser uebernimmt das "Data-juggling" in den Controller, wo wir, so die Idee, auf
die Felder des GameDtos zugreifen koennen. Darueber kann man dan via Binding arbeiten oder direkt die Felder
aktualisieren, aber so waere zumindest schonmal das DTO an einer Stelle wo es fuer weitere Gamelogik/UI Dinge genutzt
werden kann.

## Gamelogik

Das meiste geschieht in digestGame und setBoard. In Setboard rendern wir die Karten auf Grundlade des Boards was aus der
Nachricht vom Server gezogen wird. Vorher wird der Score und der Turn gesetzt. Dannach wird geschaut was es fuer eine
Nachricht ist. Entsprechend kommt das Handling

## Endscore

Aktuell wird der

### Fragen

- Im Client gibt es ebenfalls Enums, die wir verwendet wollten, allerdings bekommen wir dort nur Enumobjekte
- Warum matchdto im state???
- Wie machen wir das mit dem Endscore? Fuer Chris und mich ist es okay das einfach auf eine weitere View zu leiten Wo
  wir nur den GEwinner ausgeben und das wars, mehr steht da nicht Damit koennen wir dann die Socketverbindung noch
  beenden und das SPiel komplett beenden