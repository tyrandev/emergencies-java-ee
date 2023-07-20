function createActivity(arg1){
	let divFrequence= document.getElementById("frequence");
	let divFre=document.getElementById("fre");
	
	if(arg1!="event"){
		divFrequence.style.display="block";
		if(arg1=="course"){
			divFre.style.display="block";
		}else{
			divFre.style.display="none";
		}
		
	}else{
		divFrequence.style.display="none";
		divFre.style.display="none";
	}
}

function getValue(){
	let input= $('input:checked', '#formRadio').val();
	return input;
}