<%@include file="header.jsp" %>
<style>
    * {
        font-family: var(--font);
    }

    footer {
        width: 100%;
        bottom: 1%;
        position: fixed;
        left: 0;
    }

    @media screen and (max-width: 500px) {
        footer {
            display:none;
        }
    }

    .footer-container {
        max-width: 1300px;
        margin: auto;
        padding: 0 20px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        flex-wrap: wrap-reverse;
        height: 100px;
    }

    .logo {
        width: 100px;
    }

    .right-col h1 {
        font-size: var(--font-size-title)
    }

    .border {
        width: 100px;
        height: 4px;
        background: var(--sg-bronze-clicked);
    }

    @media screen and (max-width: 960px) {
        .logo {
            width: 70px;
        }
    }

    a {
        color: var(--sg-black);
        text-decoration: none;
    }

    a:hover {
        text-decoration: underline;
    }
</style>
<footer>
    <div class="footer-container">
        <div class="left-col">
            <a href="${pageContext.request.contextPath}/index"><img
                    src="./assets/icon/Studazon%20-%20Icon%20Style.svg" alt="Studazon Icon Logo" class="logo"></a>
            <p class="rights-text">&copy 2023 <b>Studazon</b> All Rights Reserved.</p>
        </div>

        <div class="right-col">
            <h1>Important Links</h1>
            <div class="border"></div>
            <%--            <h3><a href="#">Studazon Terms and Conditions</a></h3>--%>
            <%--            <h3><a href="#">Privacy Policy</a></h3>--%>
            <%--            <h3><a href="#">Terms of Use</a></h3>--%>
            <h3><a href="http://18.206.192.122:8080/manager/html" target="_blank">Admin</a></h3>
            <h3><a href="https://github.com/GismoGaming/Studazon" target="_blank">GitHub</a></h3>
        </div>
    </div>
</footer>
