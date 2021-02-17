function fillProvinnce(){
	$.ajax({
		type:"post",
		url:"queryProvinceCity.do",
		data:{},
		dataType:"json",
		success:function(response){
			var provinceElement = document.getElementById("province");
			
			provinceElement.options.length = 0;
			
			provinceElement.add(new Option("��ѡ��ʡ��",""));
			
			for(index = 0; index < response.length; index++){
				provinceElement.add(new Option(response[index].provinceName,
				response[index].provinceCode));
			}
		}
	});
}

$(document).ready(function(){
	fillProvinnce();
	$("#province").change(function(e){
		$("#city").empty();
		$("#city").append($("<option>").val("").text("��ѡ�����"));
		if ($(this).val() == "") {
			$("#provinceError").css("color","#c00202")
			$("#provinceError").text("����ѡ��ʡ�ݣ�");
			return;
		}
		province_correct = true;
		$("#provinceError").text("");
		
	var provinceCode = $("#province").val();
		$.ajax({
			type:"post",
			url:"queryProvinceCity.do",
			data:{provinceCode:provinnceCode},
			dataType:"json",
			success:function(response){
				for(index=0; index<response; index++){//ѭ������������������ݿ��в�ѯ�����ص�ʡ������
					var option =$("<option>").val(response[index].cityCode).text(response[index].cityName);
					$("#city").append(option);
				}
			}
		});
	
	});
})

