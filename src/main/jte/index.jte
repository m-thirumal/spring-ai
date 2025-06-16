//@params(String response, String question)

<!DOCTYPE html>
<html>
<head>
    <title>Ask a Question</title>
</head>
<body>
    <h1>Ask a Question to Ollama</h1>

    <form action="/ollama/ask-question" method="post">
        <label for="question">Your Question:</label><br/>
        <input type="text" id="question" name="question" required/><br/><br/>
        <button type="submit">Submit</button>
    </form>
<!--
    @if (response != null && !response.isEmpty()) {
        <h2>Response:</h2>
        <p>@response</p>

        <h3>Your Question:</h3>
        <p>@question</p>
    }-->
</body>
</html>
