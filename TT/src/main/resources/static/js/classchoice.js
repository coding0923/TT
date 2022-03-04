/**
 * 
 */
  
    //선생님이 속한 반의 인덱스 구해서 세션에 선택한 반과 학원정보 넣어주는함수
    function indexcheck(idx){
      var index = $(idx).index()
      let role = $("#role").val();
       var academyClassdata = {
        classId : 'class',
        className : 'classname',
        academyId : 'academyid',
        academyName : 'academyname'
       }
       academyClassdata.classId = $("#classId"+index).text();
       academyClassdata.className= $("#className"+index).text();
       academyClassdata.academyId= $("#academyId"+index).text();
       academyClassdata.academyName= $("#academyName"+index).text();
       
        $.ajax({
           url:'/classs/classchoice', //Controller에서 인식할 주소
           type:'post', //POST 방식으로 전달
           contentType: 'application/json',
           data: JSON.stringify(academyClassdata),
           success:function(data){
                   console.log(data);
                   if(data == 1){
                      if(role =='teacher'){
                        location.href="/attendee/attendeelist";
                      }else{
                        location.href="/classs/classdetails"+"?"+"classId="+academyClassdata.classId;
                    }
                   }else{
                       
                    }
           },
           error:function(){
                   alert("에러입니다");
           }
        });
     }
 