<!DOCTYPE html>
<html>
<LINK rel="stylesheet" href="../CSS/Marchi.css" type="text/css">
<!--  
	Area dedicata alla descrizione dei marchi collegati al sito
-->



<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="../JS/Marchi.js"></script>
</head>
<body>

		<!-- Tabela 3x3 con loghi di marchi a caso -->
		<table style="width:100%">
			<tr>
				<th>
					<a onclick = 'pfunc("Oakley")'>
						<img src="http://www.luxottica.com/sites/luxottica.com/files/brand/logo_grid/oakley.png" width="400" height="140" alt="Oakley" title="Oakley">
					</a>
				</th>
				<th>
					<a onclick = 'pfunc("Alain Mikli")'>
						<img src="http://www.luxottica.com/sites/luxottica.com/files/brand/logo_grid/alainmikli-400-griglia.jpg" width="400" height="140" alt="Alain Mikli" title="Alain Mikli">
					</a>
				</th>
				<th>
					<a onclick = 'pfunc("Arnette")'>
						<img src="http://www.luxottica.com/sites/luxottica.com/files/brand/logo_grid/arnette-400-griglia.jpg" width="400" height="140" alt="Arnette" title="Arnette">
					</a>
				</th>
			</tr>
			<tr>
				<th>
					<a onclick = 'pfunc("Brooks Brothers")'>
						<img src="http://www.luxottica.com/sites/luxottica.com/files/brand/logo_grid/brooks-brothers.png" width="400" height="140" alt="Brooks Brothers" title="Brooks Brothers">
					</a>
				</th>
				<th>
					<a onclick = 'pfunc("Burberry")'>
						<img src="http://www.luxottica.com/sites/luxottica.com/files/brand/logo_grid/burberry-new.png" width="400" height="140" alt="Burberry" title="Burberry">
					</a>
				</th>
				<th>
					<a onclick = 'pfunc("Bvlgari")'>
						<img src="http://www.luxottica.com/sites/luxottica.com/files/brand/logo_grid/bvlgari.png" width="400" height="140" alt="Bvlgari" title="Bvlgari">
					</a>
				</th>
			</tr>
			<tr>
				<th>
					<a onclick = 'pfunc("Chanel")'>
						<img src="http://www.luxottica.com/sites/luxottica.com/files/brand/logo_grid/chanel.png" width="400" height="140" alt="Chanel" title="Chanel">
					</a>
				</th>
				<th>
					<a onclick = 'pfunc("Coach")'>
						<img src="http://www.luxottica.com/sites/luxottica.com/files/brand/logo_grid/coach-2015-400-term.png" width="400" height="140" alt="">
					</a>
				</th>
				<th>
					<a onclick = 'pfunc("DKNY")'>
						<img src="http://www.luxottica.com/sites/luxottica.com/files/brand/logo_grid/dkny_small.jpg" width="400" height="140" alt="DKNY" title="DKNY">
					</a>
				</th>
			</tr>
			</table>

		<!-- Parte a scomparsa che viene modificata cliccando su un marchio -->
		<p id="puttana">
		</p>
</body>
</html>