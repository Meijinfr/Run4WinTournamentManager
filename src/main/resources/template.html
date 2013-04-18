<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
  <head>
    <style type="text/css">
    	body {font-size:12.0pt; font-family:"Times New Roman"; color:black;}                    	      
    	p	{margin-right:0cm; margin-left:0cm; font-size:12.0pt; font-family:"Times New Roman";}
    	.table-3 {
	border: 1px solid #DFDFDF;
	background-color: #F9F9F9;
	width: 100%;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	border-radius: 3px;
	font-family: Arial,"Bitstream Vera Sans",Helvetica,Verdana,sans-serif;
	color: #333;
}
.table-3 td, #table-3 th {
	border-top-color: white;
	border-bottom: 1px solid #DFDFDF;
	color: #555;
}
.table-3 th {
	text-shadow: rgba(255, 255, 255, 0.796875) 0px 1px 0px;
	font-family: Georgia,"Times New Roman","Bitstream Charter",Times,serif;
	font-weight: normal;
	padding: 7px 7px 8px;
	text-align: left;
	line-height: 1.3em;
	font-size: 14px;
}
.table-3 td {
	font-size: 12px;
	padding: 4px 7px 2px;
	vertical-align: top;
}
    </style>
  </head>
  <body>
    <p>
      <h2>${name} du ${date}</h2>
    </p>
    <p>
    	<h3>Liste des joueurs inscrits :</h3>
    	<table class="table-3">
    		<thead>
    			<th>Pr&#233;nom</th>
    			<th>Nom</th>
    			<th>Pseudo</th>
    			<th>Id Corporation</th>
    			<th>Id Runner</th>
    		</thead>
    		#foreach( $player in $players )
    		<tr>
    			<td>$player.firstName</td>
    			<td>$player.lastName</td>
    			<td>$player.nickname</td>
    			<td>
	    			#if($player.idCorporation != "")
	    				<img src="$identityConverter.coerceToHTML($player.idCorporation)" height="30" width="30"/>
	    			#else
	    				$player.idCorporation
	    			#end
    			</td>
    			<td>
	    			#if($player.idRunner != "")
		    			<img src="$identityConverter.coerceToHTML($player.idRunner)" height="30" width="30"/>
		    		#else
		    			$player.idRunner
		    		#end
	    		</td>
    		</tr>
    		#end
		</table>
    </p>
    <p>
    	<h3>R&#233;sultats des joueurs par ronde :</h3>
    	#foreach ( $round in $rounds )
    		<p>
    			<b>Ronde $round.roundNumber</b>
    			<table class="table-3">
    				<thead>
    					<th>N. Table</th>
    					<th>Pseudo</th>
    					<th>R&#233;sultat Corporation</th>
    					<th>R&#233;sultat Runner</th>
    				</thead>
	    			#foreach ( $game in $round.games )
	    			<tr>
	    				<td>$game.tableNumber</td>
	    				<td>$game.player1.nickname</td>
	    				<td>
	    					#if($game.player1.idCorporation != "")
		    					<img src="$identityConverter.coerceToHTML($game.player1.idCorporation)" height="30" width="30"/>
		    				#end
		    				$game.p1Result.resultCorporation 
	    					#if($game.p1Result.flatlineWin)
							(Flatline)
	    					#end
	    				</td>
	    				<td>
	    					#if($game.player1.idRunner != "")
		    					<img src="$identityConverter.coerceToHTML($game.player1.idRunner)" height="30" width="30"/>
		    				#end
		    				$game.p1Result.resultRunner 
	    					#if($game.p1Result.millWin)
							(Meule)
	    					#end
	    				</td>
	    			</tr>
	    			<tr>
	    				<td>$game.tableNumber</td>
	    				<td>$game.player2.nickname</td>
	    				<td>
	    					#if($game.player2.idCorporation != "")
		    					<img src="$identityConverter.coerceToHTML($game.player2.idCorporation)" height="30" width="30"/>
		    				#end
		    				$game.p2Result.resultCorporation 
	    					#if($game.p2Result.flatlineWin)
							(Flatline)
	    					#end
	    				</td>
	    				<td>
	    					#if($game.player2.idRunner != "")
		    					<img src="$identityConverter.coerceToHTML($game.player2.idRunner)" height="30" width="30"/>
		    				#end
		    				$game.p2Result.resultRunner 
	    					#if($game.p2Result.millWin)
							(Meule)
	    					#end
	    				</td>
	    			</tr>
	    			#end
    			</table>
    		</p>
    	#end
    </p>
    <p>
    	<h3>Classement final des joueurs :</h3>
    	<table class="table-3">
    		<thead>
    			<th>Rang</th>
    			<th>Pseudo</th>
    			<th>Prestige pris</th>
    			<th>Force Adversaire</th>
    			<th>Agendas pris</th>
    			<th>Agendas laiss&#233;s</th>
    		</thead>
    		#set( $i = 1 )
    		#foreach( $player in $players )
    		<tr>
    			<td>$i</td>
    			<td>$player.nickname</td>
    			<td>$player.prestige</td>
    			<td>$player.opponentsStrength</td>
    			<td>$player.points</td>
    			<td>$player.opponentsPoints</td>
    		</tr>
    		#set( $i = $i+1 )
    		#end
		</table>
    </p>
  </body>
</html>