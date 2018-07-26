<!DOCTYPE html>
<html>
<LINK rel="stylesheet" href="../CSS/Marco.css" type="text/css">
<!--  
	Area dedicata alla descrizione dei marchi collegati al sito
-->



<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/JS/Marco.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/JS/jquery-3.3.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/JS/ajax.js"></script>
</head>
<body>
<div class= "header">
<%@ include file="Header.jsp"%>
</div>

		<!-- Tabela 3x3 con loghi di marchi a caso -->
		<table class="table-responsive">
			<tr>
				<th>
					<a onclick = 'pfunc("Oakley")'>
						<img src="http://www.luxottica.com/sites/luxottica.com/files/brand/logo_grid/oakley.png" alt="Oakley" title="Oakley">
					</a>
				</th>
				<th>
					<a onclick = 'pfunc("AlainMikli")'>
						<img src="http://www.luxottica.com/sites/luxottica.com/files/brand/logo_grid/alainmikli-400-griglia.jpg" alt="Alain Mikli" title="Alain Mikli">
					</a>
				</th>
				<th>
					<a onclick = 'pfunc("Arnette")'>
						<img src="http://www.luxottica.com/sites/luxottica.com/files/brand/logo_grid/arnette-400-griglia.jpg" alt="Arnette" title="Arnette">
					</a>
				</th>
			</tr>
			<tr>
				<th>
					<a onclick = 'pfunc("BrooksBrothers")'>
						<img src="http://www.luxottica.com/sites/luxottica.com/files/brand/logo_grid/brooks-brothers.png" alt="Brooks Brothers" title="Brooks Brothers">
					</a>
				</th>
				<th>
					<a onclick = 'pfunc("Burberry")'>
						<img src="http://www.luxottica.com/sites/luxottica.com/files/brand/logo_grid/burberry-new.png" alt="Burberry" title="Burberry">
					</a>
				</th>
				<th>
					<a onclick = 'pfunc("Bvlgari")'>
						<img src="http://www.luxottica.com/sites/luxottica.com/files/brand/logo_grid/bvlgari.png" alt="Bvlgari" title="Bvlgari">
					</a>
				</th>
			</tr>
			<tr>
				<th>
					<a onclick = 'pfunc("Chanel")'>
						<img src="http://www.luxottica.com/sites/luxottica.com/files/brand/logo_grid/chanel.png" alt="Chanel" title="Chanel">
					</a>
				</th>
				<th>
					<a onclick = 'pfunc("Coach")'>
						<img src="http://www.luxottica.com/sites/luxottica.com/files/brand/logo_grid/coach-2015-400-term.png" alt="">
					</a>
				</th>
				<th>
					<a onclick = 'pfunc("DKNY")'>
						<img src="http://www.luxottica.com/sites/luxottica.com/files/brand/logo_grid/dkny_small.jpg"alt="DKNY" title="DKNY">
					</a>
				</th>
			</tr>
			</table>
			
		<div id="Bvlgari"  class="hide-me"><h3>BVLGARI</h3>
			<p>
				In licenza dal 1997, Bulgari, il gioielliere italiano di fama internazionale, maestro nella lavorazione delle gemme, rappresenta uno dei più prestigiosi brand di occhiali grazie al design contemporaneo, allo stile unico e a dettagli raffinati.
				Il marchio si colloca nel segmento più alto dell'occhialeria-gioiello grazie all'artigianalità italiana, al design audace e all'utilizzo di materiali pregiati quali oro e pietre colorate, oltre ai cristalli, applicati su creazioni esclusive e senza tempo.
			</p>
		</div>

		<!-- Parte a scomparsa che viene modificata cliccando su un marchio -->
		<div id="peppe">
			</div>
		
		<div id="Oakley" class="hide-me"><h3>OAKLEY</h3>
			<p>
			Il marchio Oakley, fondato nel 1975 e acquisito da Luxottica nel 2007, è uno dei nomi più importanti dell'occhialeria sportiva, su cui fanno affidamento atleti di rilevanza mondiale per competere ai massimi livelli.
			Oakley, titolare di oltre 800 brevetti, è nota per la tecnologia innovativa delle sue lenti, in particolare per il brevetto High Definition Optics.
			Oltre che sugli occhiali da sole e da vista e sulle maschere da sci, il marchio Oakley è presente con collezioni di abbigliamento e accessori rivolti ad appassionati di sport e amanti del casual.
			</p>
		</div>
		
		<div id="AlainMikli" class="hide-me"><h3>ALAIN MIKLI</h3>
		<p>
		Acquisito da Luxottica nel 2013, Alain Mikli da sempre parla ad un pubblico di artisti e creativi in tutto il mondo.
		Dal 1978, il marchio è sinonimo di distinzione e provocazione, grazie al suo design unico e alle combinazioni di colori esclusive.
		Connubio tra opera d'arte e prodotto di consumo, la montature diventa un oggetto moda per vedere ed essere visti.
		</p>
		</div>
		
		<div id="Arnette" class="hide-me"><h3>ARNETTE</h3>
		<p>
		Creato nel 1992 in California e acquisito da Luxottica nel 1999, Arnette è un marchio active lifestyle pensato per chi è sempre alla ricerca di uno stile easy-going e di nuove esperienze.
		Tratti chiave di Arnette sono senza dubbio la qualità funzionale a un prezzo accessibile, pensata per un uso quotidiano, e l'amore autentico per sport freestyle quali il surf e lo skate.
		</p>
		</div>
		
		<div id="BrooksBrothers" class="hide-me"><h3>BROOKS BROTHERS</h3>
		<p>
		Caratterizzate da materiali leggeri e da una linea sottile, le collezioni Brooks Brothers rispecchiano i tratti peculiari dello stile del marchio americano.
		È una linea di prodotti accessibili a tutti, dallo stile classico che offre funzionalità, leggerezza e alta qualità.
		La licenza è stata stipulata per la prima volta nel 1992.
		</p>
		</div>
		
		<div id="Burberry" class="hide-me"><h3>BURBERRY</h3>
		<p>
		Dalla sua nascita nel 1856 in Inghilterra Burberry è stato sinonimo di design, artigianalità e innovazione.
		In licenza dal 2006, Burberry è oggi diventato un marchio leader nel mercato dell'alta moda e del lusso con magnifici risultati a livello globale e una distintiva identità "british".
		Gli occhiali si ispirano all'innovativa collezione del ready-to-wear e agli accessori, giocando con le icone del marchio sia per l'uomo sia per la donna.
		</p>
		</div>
		
		<div id="Chanel" class="hide-me"><h3>CHANEL</h3>
		<p>
		Gli imperdibili accessori moda Chanel celebrano l'eleganza e la femminilità.
		Dagli stili contemporanei a quelli iconici, i design dimostrano la maestria e la creatività della casa di moda Chanel.
		Nel corso delle stagioni, le collezioni vengono costantemente reimmaginate per completare le linee Chanel ed esprimere lo stile di una donna.
		</p>
		</div>
		
		<div id="Coach" class="hide-me"><h3>COACH</h3>
		<p>
		Fondato nel 1941 come laboratorio di famiglia in un loft di Manhattan, Coach è diventato uno dei principali marchi statunitensi di accessori e abbigliamento femminile e maschile.
		In licenza dal 2012, la collezione eyewear di Coach esprime alla perfezione il naturale stile newyorkese e l'autentico patrimonio americano del brand.
		</p>
		</div>
		
		<div id="DKNY" class="hide-me"><h3>DKNY</h3>
		<p>
		DKNY è una linea di moda confortevole, caratterizzata dall'energia di New York City: attiva, metropolitana, divertente, veloce, reale.
		Si rivolge a donne moderne, sicure di sé e attente alla moda, soddisfacendo le esigenze di uno stile di vita sempre in movimento, al lavoro e nel weekend, di giorno e di notte.
		In licenza dal 2005, DKNY eyewear propone un design moderno a un prezzo accessibile: perfetto connubio di valore, qualità e stile.
		</p>
		</div>
		
</body>
</html>