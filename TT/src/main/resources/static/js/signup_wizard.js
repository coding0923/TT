(function($) {

    var form = $("#signup-form");
    
    
    
      form.validate({
        errorPlacement: function errorPlacement(error, element) {
             element.before(error); 
        },
        rules: {
            consent: {
                required: true,
                id: false,
                name: false, 
            },
            teacherId : {
                required: true,
                id: true,
                name: false, 
                remote: {
                    url: '/member/idCheck',
                    type: 'POST',
                    data: {
                        teahcerId : function(){
                            return $("#username").val();  
                        }
                    }
                }
            },
            teacherPassword : {
                required: true,
                id: false,
                pw: true,
                name: false,
            },
            confirmPassword : {
                required: true,
                id: false,
                pw: false,
                name: false,
                equalTo: "#password",
            },
            teacherName : {
                required: true,
                id: false,
                pw: false,
                name: true,
            },
            teacherGender : {
                required: false,
                id: false,
                pw: false,
                name: false,
            },
            teacherBirthdate : {
                required: false,
                id: false,
                pw: false,
                name: false,
            },
            teacherPhone : {
                required: true,
                id: false,
                pw: false,
                name: false,
                phone: true,
            },
            teacherEmail : {
                required: true,
                id: false,
                pw: false,
                name: false,
                phone: false,
                email: true,
            },
            teacherSubject : {
                required: true,
                id: false,
                pw: false,
                name: false,
                phone: false,
                email: false,
            },
            teacherMajor : {
                required: true,
                id: false,
                pw: false,
                name: false,
                phone: false,
                email: false,
            },
            
        },
        onfocusout: function(element) {
            $(element).valid();
        },
        highlight : function(element, errorClass, validClass) {
            $(element.form).find('.actions').addClass('form-error');
            $(element).removeClass('valid');
            $(element).addClass('error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element.form).find('.actions').removeClass('form-error');
            $(element).removeClass('error');
            $(element).addClass('valid');
        }
    });
    
    
   
    
    form.steps({
        headerTag: "h3",
        bodyTag: "fieldset",
        transitionEffect: "fade",
        labels: {
            previous : '이전',
            next : '다음',
            finish : '가입하기',
            current : ''
        },
        titleTemplate : '<span class="title">#title#</span>',
        onStepChanging: function (event, currentIndex, newIndex)
        {
            form.validate().settings.ignore = ":disabled,:hidden";
            return form.valid();
        },
        onFinishing: function (event, currentIndex)
        {
            form.validate().settings.ignore = ":disabled";
            return form.valid();
        },
        onFinished: function (event, currentIndex)
        {
            form.submit();
            alert('가입이 완료되었습니다. 로그인을 새로 진행해주세요.');
        },
        // onInit : function (event, currentIndex) {
        //     event.append('demo');
        // }
    });

    jQuery.extend(jQuery.validator.messages, {
        required: "",
        remote: "",
        email: "",
        url: "",
        date: "",
        dateISO: "",
        number: "",
        digits: "",
        creditcard: "",
        equalTo: ""
    });
    
    

    $('#gender').parent().append('<ul class="list-item" id="newgender" name="gender"></ul>');
    $('#gender option').each(function(){
        $('#newgender').append('<li value="' + $(this).val() + '">'+$(this).text()+'</li>');
    });
    $('#gender').remove();
    $('#newgender').attr('id', 'gender');
    $('#gender li').first().addClass('init');
    $("#gender").on("click", ".init", function() {
        $(this).closest("#gender").children('li:not(.init)').toggle();
    });
    
    var allOptions = $("#gender").children('li:not(.init)');
    $("#gender").on("click", "li:not(.init)", function() {
        allOptions.removeClass('selected');
        $(this).addClass('selected');
        $("#gender").children('.init').html($(this).html());
        allOptions.toggle();
    });

    $('#country').parent().append('<ul class="list-item" id="newcountry" name="country"></ul>');
    $('#country option').each(function(){
        $('#newcountry').append('<li value="' + $(this).val() + '">'+$(this).text()+'</li>');
    });
    $('#country').remove();
    $('#newcountry').attr('id', 'country');
    $('#country li').first().addClass('init');
    $("#country").on("click", ".init", function() {
        $(this).closest("#country").children('li:not(.init)').toggle();
    });
    
    var CountryOptions = $("#country").children('li:not(.init)');
    $("#country").on("click", "li:not(.init)", function() {
        CountryOptions.removeClass('selected');
        $(this).addClass('selected');
        $("#country").children('.init').html($(this).html());
        CountryOptions.toggle();
    });

    $('#payment_type').parent().append('<ul  class="list-item" id="newpayment_type" name="payment_type"></ul>');
    $('#payment_type option').each(function(){
        $('#newpayment_type').append('<li value="' + $(this).val() + '">'+$(this).text()+'</li>');
    });
    $('#payment_type').remove();
    $('#newpayment_type').attr('id', 'payment_type');
    $('#payment_type li').first().addClass('init');
    $("#payment_type").on("click", ".init", function() {
        $(this).closest("#payment_type").children('li:not(.init)').toggle();
    });
    
    var PaymentsOptions = $("#payment_type").children('li:not(.init)');
    $("#payment_type").on("click", "li:not(.init)", function() {
        PaymentsOptions.removeClass('selected');
        $(this).addClass('selected');
        $("#payment_type").children('.init').html($(this).html());
        PaymentsOptions.toggle();
    });

    $.dobPicker({
        daySelector: '#birth_date',
        monthSelector: '#birth_month',
        yearSelector: '#birth_year',
        dayDefault: 'Day',
        monthDefault: 'Month',
        yearDefault: 'Year',
        minimumAge: 20,
        maximumAge: 50
    });

    
  $.validator.addMethod(
    'id',
    function (id, element) {
      return (
         /^[a-z]+[a-z0-9]{5,11}$/.test(
          id
        )
      );
    },
    '올바른 아이디 형식이 아닙니다'
  );
  
  $.validator.addMethod(
    'pw',
    function (password, element) {
      return (
        /^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,16})/.test(
          password
        )
      );
    },
    '올바른 비번 형식이 아닙니다'
  );
  
  $.validator.addMethod(
    'name',
    function (name, element) {
      return (
          this.optional(element)||/^[가-힣]{2,4}$/.test(
          name
        )
      );
    },
    '올바른 이름 형식이 아닙니다'
  );
  
  $.validator.addMethod(
    'phone',
    function (phone, element) {
      return (
          this.optional(element)||/^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/.test(
          phone
        )
      );
    },
    '올바른 핸드폰 번호 형식이 아닙니다'
  );
  
   $.validator.addMethod(
    'email',
    function (email, element) {
      return (
         /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(
          email
        )
      );
    },
    '올바른 이메일 형식이 아닙니다'
  );
  
  
  
        
})(jQuery);