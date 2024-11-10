import stix2
import uuid

# Example: Load a STIX 2.0 bundle (this can be downloaded or generated)
stix_file = "path_to_stix_file.json"  # Path to your STIX 2.0 data file

# Function to generate a valid STIX identifier
def generate_valid_stix_id():
    return f"bundle--{uuid.uuid4()}"

# Parse the STIX file
with open(stix_file, 'r') as file:
    bundle = stix2.parse(file.read())

# Ensure bundle 'id' is valid (replace if invalid)
if not bundle.id.startswith("bundle--"):
    bundle.id = generate_valid_stix_id()

# Example of extracting techniques, adversaries, or other data
for obj in bundle.objects:
    if obj['type'] == 'intrusion-set':  # Example: Extracting adversaries
        print(f"Adversary: {obj['name']}")
    if obj['type'] == 'attack-pattern':  # Example: Extracting attack techniques
        print(f"Attack Technique: {obj['name']}")
    if obj['type'] == 'indicator':  # Example: Extracting indicators (e.g., IP addresses)
        print(f"Indicator: {obj['pattern']}")
