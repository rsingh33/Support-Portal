<script type="text/javascript">
    function validateJira() {
        var jira = document.forms["editForm"]["tracking"].value;
        if (!(jira == "" || jira.length <= 12)) {
            alert("This is not a valid jira number, please enter the jira number like C167433-123")
            return false;
        }
    }
</script>