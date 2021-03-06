/**
 * 
 */
  let insertForm = $("#insertForm");
  
  
  
  console.log('스크립트진입');
  $("#classIdAll").on("click",function(){
      if($("#classIdAll").is(":checked")){
          $("input[name=check]").prop("checked",true);
      }else{
          $("input[name=check]").prop("checked",false);
      }
  })
  
  $(".attendance_btn").on("click",function(e){
      e.preventDefault();
      let btnValue = this.value;
      console.log(btnValue);
      checkboxpoint(btnValue);
  });
  //체크박스 체크된거확인
  function checkboxpoint(btntext){
      var checkArr = [];
      $('input:checkbox[name=check]:checked').each(function() {
          checkArr.push(this.value); 
          console.log(checkArr);
      });
      checkArr.forEach(function(i){
          switch(btntext){
            case '출석':
             $("#attendanceStatus"+i).text(btntext);
             $("#attendanceStatuscolor"+i).attr('class','status text-success');
             
            break;
               case '결석':
             $("#attendanceStatus"+i).text(btntext);
             $("#attendanceStatuscolor"+i).attr('class','status text-danger');
            break;
               case '지각':
             $("#attendanceStatus"+i).text(btntext);
             $("#attendanceStatuscolor"+i).attr('class','status text-warning');
            break;
               case '조퇴':
             $("#attendanceStatus"+i).text(btntext);
             $("#attendanceStatuscolor"+i).attr('class','status text-warning');
            break;
          }
      });
      $("input[name=check]").prop("checked",false);
      $("#classIdAll").prop("checked",false);
  }
  //DB에 아이디 학원 반 출석할 날짜 출석상태 저장 
  $("#saveBtn").on("click",function(e){
       e.preventDefault();
       var length = document.getElementById("atdcTableBody").getElementsByTagName("tr").length;
       var Attendencedatas =[]; 
       for(let i=0;i<length;i++){
           console.log(i);
           console.log('for문진입');
           var Attendencedata ={
                      attendanceDate : 'date', 
                      studentId : 'id', 
                      classId : 'classid',
                      academyId : 'academyId',
                      attendanceStatus : 'attendenceType',
                      attendanceMemo : 'memo',
                      insertUser : 'insertUser',
                      updateUser : 'updateUser'
           }
           Attendencedata.attendanceDate = $("#attendanceDate").val();
           Attendencedata.studentId = $("#studentId"+i).text();
           Attendencedata.classId = $("#classId").val();
           Attendencedata.academyId = $("#academyId").val();
           Attendencedata.attendanceStatus = $("#attendanceStatus"+i).text();
           Attendencedata.attendanceMemo = $("#attendanceMemo"+i).val();
           Attendencedata.insertUser = $("#teacherId").val();
           Attendencedata.updateUser = $("#teacherId").val();
           Attendencedatas.push(Attendencedata);
           console.log(Attendencedatas[2])
       };
       $.ajax({
           url:'/attendance/attendanceput', //Controller에서 인식할 주소
           type:'post', //POST 방식으로 전달
           contentType: 'application/json',
           data: JSON.stringify(Attendencedatas),
           success:function(data){
                   console.log(data);
                   if(data == 1){
                       alert('저장완료');
                       location.reload();
                   }else{
                       
                    }
           },
           error:function(){
                   alert("에러입니다");
           }
        });
  })
