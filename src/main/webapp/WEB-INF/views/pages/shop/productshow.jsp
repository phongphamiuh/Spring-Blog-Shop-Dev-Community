<?xml version="1.0" encoding="UTF-8" standalone="no"?>

<div xmlns:jsp="http://java.sun.com/JSP/Page"
     xmlns:c="http://java.sun.com/jsp/jstl/core"
     xmlns:spring="http://www.springframework.org/tags"
     version="2.0">
     
    <jsp:directive.page contentType="text/html;charset=UTF-8"/>
    
    <spring:url value="/shop" var="showShopUrl"/>    
    
    <script type="text/javascript">
        $(function(){
            $("#list").jqGrid({
                url:'${showShopUrl}/shop',
                datatype: 'json',
                mtype: 'GET',
                colNames:"Book Name", "Author","Price",
                colModel :[
                    {name:'bookName', index:'bookName', width:150},
                    {name:'author', index:'author', width:100},
                    {name:'price', index:'price', width:100}
                ],
                jsonReader : {
                    root:"singerData",
                    page: "currentPage",
                    total: "totalPages",
                    records: "totalRecords",
                    repeatitems: false,
                    id: "id"
                },
                pager: '#pager',
                rowNum:10,
                rowList:[10,20,30],
                sortname: 'bookName',
                sortorder: 'asc',
                viewrecords: true,
                gridview: true,
                height: 250,
                width: 500,
                caption: "List Book"
                
            });
        });
    </script>
    <div>
        <table id="list"><tr><td/></tr></table>
    </div>
    <div id="pager"></div>
</div>