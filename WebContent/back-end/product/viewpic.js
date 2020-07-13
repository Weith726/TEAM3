$(document).ready(function(){
			var reader = new FileReader(); 
			$("#pic").change(function(){
				let myfile = this.files;
				$(reader).on("load",function(e){
					$("#img").attr("src",e.target.result);
				});
				reader.readAsDataURL(myfile[0]);
			}); 
	})