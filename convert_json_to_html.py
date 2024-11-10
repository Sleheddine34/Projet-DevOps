import json
from json2html import json2html

with open("snyk-report.json") as json_file:
    data = json.load(json_file)

html_content = json2html.convert(json=data)

with open("snyk-report.html", "w") as html_file:
    html_file.write(html_content)
