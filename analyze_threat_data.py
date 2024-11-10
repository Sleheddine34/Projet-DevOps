import stix2

# Example: Load a STIX 2.0 bundle (this can be downloaded or generated)
# Update the path to where your STIX file is located
stix_file = "stix_files/your_stix_file.json"  # Path to your STIX 2.0 data file

# Parse the STIX file
with open(stix_file, 'r') as file:
    bundle = stix2.parse(file.read())

# Example of extracting techniques, adversaries, or other data
for obj in bundle.objects:
    if obj['type'] == 'intrusion-set':  # Example: Extracting adversaries
        print(f"Adversary: {obj['name']}")
    if obj['type'] == 'attack-pattern':  # Example: Extracting attack techniques
        print(f"Attack Technique: {obj['name']}")
    if obj['type'] == 'indicator':  # Example: Extracting indicators (e.g., IP addresses)
        
