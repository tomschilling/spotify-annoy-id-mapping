from __future__ import print_function
import sys
from annoy import AnnoyIndex

t = AnnoyIndex(150)
fn = '/Volumes/My Book/WikipediaDaten Word2vec/wiki.angular.annoy100'

t.load(fn)

print(t.get_nns_by_item(int(sys.argv[1]), 10, 10000))
