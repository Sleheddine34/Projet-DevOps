import json
import sys

def convert_to_html(json_file, html_file):
    with open(json_file, 'r') as f:
        data = json.load(f)

    with open(html_file, 'w') as f:
        f.write('<html><head><title>Trivy Report</title></head><body>')
        f.write('<h1>Trivy Vulnerability Report</h1>')
        for vuln in data.get('Results', []):
            f.write(f"<h2>{vuln.get('Target')}</h2><ul>")
            for finding in vuln.get('Vulnerabilities', []):
                f.write(f"<li>{finding.get('VulnerabilityID')}: {finding.get('Title')}</li>")
            f.write('</ul>')
        f.write('</body></html>')

if __name__ == "__main__":
    convert_to_html(sys.argv[1], sys.argv[2])
