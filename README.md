HTML Text Highlighter
===

A simple Java command line application that applies color tags to a HTML file.
The HTML file with the color tags can then be used by another application to
highlight parts of the HTML file.

For example, given the following input:

```
<html>
<head>
<title>HTML highlight test page</title>
</head>
<body>
This is text in the body.
<br>
<h1>This is a heading</h1>
<p>This is a paragraph.</p>
There is more text in the body after the paragraph.
</body>
</html>
```

This program will output:

```
\color[RED]<html>
\color[YELLOW]<head>
\color[GREEN]<title>HTML highlight test page</title>
\color[YELLOW]</head>
\color[TURQUOISE]<body>
This is text in the body.
\color[BLUE]<br>
\color[PURPLE]<h1>This is a heading</h1>
\color[PINK]<p>This is a paragraph.</p>
\color[TURQUOISE]There is more text in the body after the paragraph.
</body>
\color[RED]</html>
```

To run, replace the contents `input.html` with your own HTML, and run the main
method located in `src/HighlighterMain.java`.

### Assumptions and comments

- Input file is a valid HTML file
- Input HTML file does not contain any comments
- Input file does not contain any '<' or '>' that are not part of an HTML tag
- Input file is `input.html`
- Output file is `output.txt`
