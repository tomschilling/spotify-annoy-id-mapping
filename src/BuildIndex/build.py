from __future__ import print_function
import sys
from annoy import AnnoyIndex


fn = sys.argv[1]
out = sys.argv[5]

a = AnnoyIndex(int(sys.argv[2]), sys.argv[3])
for n, l in enumerate(open(fn)):
    x = [float(f) for f in l.strip().split(',')]
    a.add_item(n, x)

a.build(int(sys.argv[4]))
a.save(out)

print('AnnoyIndex path: ' + out)



