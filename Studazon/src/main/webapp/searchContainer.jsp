<div class="search-container">
  <form action="dash">
    <input title = "Submit search" type="image" src="./assets/Interactivity/Search%20Icon.png" alt="Search Icon">
    <input title = "Search Studazon listings" class="input-field" type="text" placeholder="Search for a book..." name="searchQuery"
           <% if(request.getParameter("searchQuery")!=null) { %>value="<%=request.getParameter("searchQuery")%>"<% } %>>
  </form>
</div>
