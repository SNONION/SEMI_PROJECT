<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>



   <div class="outer">
   
    <!-- 게시글 구문 -->
    <h2 align="center">게시글 상세보기</h2>
    
    
    <table border="1" align="center">
    
      <tr>
      
       <th width="70">카테고리</th>
       <td width="60">${b.categoryNo }</td>
       <th width="70">제목</th>
       <td width="350">${b.boardTitle }</td>
      
      
    </tr>
    
        <tr>
    
        <th width="80">작성자</th>
        <td width="60">${b.boardWriter} </td>
        <th width="70">작성일</th>
        <td width="340">${b.createDate }</td>
        
        </tr>
        
        
          <th>내용</th>
          <td colspan="3">
          
          <p style="height:200px; white-space:pre;">${b.boardContent}</p>
          
          
             </td>
             
             </tr>
          
    </table>
    
    
       <c:if test="${loginUser.userId == b.boardWriter or loginUser.userId eq 'admin'}">
         
         <br>
         
         <div align="center">
         
           <button type="button" onclick="location.href='${contextPath}/UnionBoardDetailController.do?bno=${b.boardNo }'">수정하기</button>
           <button  type="button" id="deleteBtn">삭제하기</button>
         
         </div>
         
         </c:if>
         
         
         <br>
         
         <div id="reply-area">
         
         
           <table border="1" align="center">
           
           
              <thead>
              
               <c:choose>
               
               <c:when test="${not empty loginUser }">
               
               
               </c:when>
               
               </c:choose>
               
           </table>
           
         
         
         </div>
         
         
         
         
         
         
         
         
         
     
   </div>
   

</body>
</html>