<script type="text/javascript">
    $(document).ready(function () {
        $('#myTable').after('<div id="nav"></div>');
        var rowsShown = 4;
        var rowsTotal = $('#myTable tbody tr').length;
        var numPages = rowsTotal / rowsShown;
        for (i = 0; i < numPages; i++) {
            var pageNum = i + 1;
            $('#nav').append('<a href="#" rel="' + i + '">' + pageNum + '</a> ');
        }
        $('#myTable tbody tr').hide();
        $('#myTable tbody tr').slice(0, rowsShown).show();
        $('#nav a:first').addClass('active');
        $('#nav a').bind('click', function () {

            $('#nav a').removeClass('active');
            $(this).addClass('active');
            var currPage = $(this).attr('rel');
            var startItem = currPage * rowsShown;
            var endItem = startItem + rowsShown;
            $('#myTable tbody tr').css('opacity', '0.0').hide().slice(startItem, endItem).css('display', 'table-row').animate({opacity: 1}, 300);
        });
    });

    $(document).ready(function () {
        $("a.delete").click(function (e) {
            if (!confirm('Are you sure you want to delete?')) {
                e.preventDefault();
                return false;
            }
            return true;
        });
    });

    function onDeleteClick(event) {


        var doDelete = confirm("Are you sure you want to delete");

        if (doDelete == false) {
            event.preventDefault();
        }
    }

    function onDelete() {
        $("#delete").click(onDeleteClick);
    }

    $(document).ready(onDelete);

    function filter() {
        // Declare variables
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("myInput");
        filter = input.value.toUpperCase();
        table = document.getElementById("myTable");
        tr = table.getElementsByTagName("tr");

        // Loop through all table rows, and hide those who don't match the search query
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[0];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
</script>