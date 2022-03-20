function deleteQuestion(qid) {
    if (confirm("문제를 삭제할까요?")) {
        var addForm = document.createElement("form");
        addForm.setAttribute("charset","UTF-8");
        addForm.setAttribute("method", "Post");  //Post 방식
        addForm.setAttribute("action", "/deleteQuestion"); 
                    
        var hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", "qid");
        hiddenField.setAttribute("value", qid);
        addForm.appendChild(hiddenField);
                    
        functionDiv.appendChild(addForm);
        addForm.submit();
    }
};

function start() {
    document.getElementById('questionContent').readOnly = false;
    document.getElementById('questionScore').readOnly = false;
    document.getElementById('questionAnswer').readOnly = false;
    
    document.getElementById('updateStart').style.display = 'none';
    document.getElementById('delete').style.display = 'none';
    document.getElementById('updateEnd').style.display = 'inline-block';
    
    document.getElementById('h1Title').innerText = '문제 정보 수정 가능';
};

function end() {
    if(confirm("수정하시겠습니까?")) {
        updateQuestionForm.submit();
    }
};