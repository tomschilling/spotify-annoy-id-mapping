from __future__ import print_function
import sys
from annoy import AnnoyIndex

t = AnnoyIndex(150)
fn = sys.argv[1]

t.load(fn)

print(t.get_nns_by_item(int(sys.argv[2]), int(sys.argv[4]), int(sys.argv[3])))
