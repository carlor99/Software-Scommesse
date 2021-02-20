# Software-Scommesse

<b> res/file_creati </b><br>
Nella cartella <b> Link sorgenti </b> è presente un file .txt con i link delle partite per ogni campionato. <br>
Nella cartella <b> Pronostici partite </b> è presente un file .csv generato in base ai .csv sorgenti. <br><br>
<b> res/file_sorgenti </b><br>
La cartella <b> soccerstats.com </b> contiene i .csv generati per ogni campionato. <br><br>
<b> src </b><br>
<b> ScrapperCSVCreator </b> legge il contenuto dei link e acquisisce le informazioni necessarie per generare i .csv sorgenti.
<b> LettoreCSV </b> analizza le partite e genera le probabilità per ogni partita.<br><br>
<b> lib </b><br>
Contiene le librerie <i> Jsoup </i> e <i> OpenCSV </i> necessarie per lo scrapping HTML e l'I/O dei .csv.
