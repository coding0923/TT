function deleteQuestion(qid) {
    if (confirm("문제를 삭제할까요?")) {
        var addForm = document.createElement("form");
        addForm.setAttribute("charset","UTF-8");
        addForm.setAttribute("method", "Post");  //Post 방식
        addForm.setAttribute("action", "/test/deleteQuestion.html"); 
                    
        var hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", "qid");
        hiddenField.setAttribute("value", qid);
        addForm.appendChild(hiddenField);
                    
        deleteDiv.appendChild(addForm);
        addForm.submit();
    }
}