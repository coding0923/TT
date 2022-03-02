/**
 * 
 */
    $(document).ready(function(){
            $('[data-toggle="tooltip"]').tooltip();
        });
            
        $("#classIdAll").on("click",function(){
             /*e.preventDefault(); */
            console.log('체크박스All진입');  
            if($("#classIdAll").is(":checked")){
                  $("input[name=check]").prop("checked",true);
              }else{
                  $("input[name=check]").prop("checked",false);
              }
          })
          
        $("#callBtn").on("click",function(e){
              e.preventDefault(); 
              var checkArr = []; 
               $('input:checkbox[name=check]:checked').each(function() {
                   checkArr.push(this.value); 
                   console.log(checkArr[1]);
               })
               
               $.ajax({
                   url:'/attendee/attendeecall', //Controller에서 인식할 주소
                   type:'post', //POST 방식으로 전달
                   data:{userIds:checkArr},
                   success:function(data){
                           console.log(data);
                           if(data == 1){
                               location.reload();
                           }else{
                               
                            }
                   },
                   error:function(){
                           alert("에러입니다");
                   }
                });
          })
          
          $("#deleteBtn").on("click",function(e){
               e.preventDefault();
               var checkArr = []; 
               $('input:checkbox[name=check]:checked').each(function() {
                   checkArr.push(this.value); 
                   console.log(checkArr[1]);
               })

               $.ajax({
                   url:'/attendee/attendeedelete', //Controller에서 인식할 주소
                   type:'post', //POST 방식으로 전달
                   data:{userIds:checkArr},
                   success:function(data){
                           console.log(data);
                           if(data == 1){
                               location.reload();
                           }else{
                               
                            }
                   },
                   error:function(){
                           alert("에러입니다");
                   }
                });
          })
          
          $("#giveUpBtn").on("click",function(e){
              e.preventDefault();
               var checkArr = []; 
               $('input:checkbox[name=check]:checked').each(function() {
                   checkArr.push(this.value); 
                   console.log(checkArr[1]);
               })

               $.ajax({
                   url:'/attendee/attendeegiveup', //Controller에서 인식할 주소
                   type:'post', //POST 방식으로 전달
                   data:{userIds:checkArr},
                   success:function(data){
                           console.log(data);
                           if(data == 1){
                               location.reload();
                           }else{
                               
                            }
                   },
                   error:function(){
                           alert("에러입니다");
                   }
                });
          })
       
    
 