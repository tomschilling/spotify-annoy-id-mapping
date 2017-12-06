# spotify-annoy-id-mapping

## Install

1. Please clone or download this repository
2. Get the [spotiy/annoy](https://github.com/spotify/annoy) python package from [PyPI](https://pypi.python.org/pypi/annoy)


## How it works

1. Build your annoy index with `BuildIndex`  
2. Give the `indexpath` to the created annoy index for nearest neighbor search
2. How many search nodes do you want for `k` ( see [*Tradeoffs*](https://github.com/spotify/annoy#tradeoffs))
3. Give a `n` for how many results you want to get back as closest items

## Where is the loaded index stored? 

Annoy loads the index with a mmap() function into the memory, there it remains until you specificly unload the index.
(see [load()](https://github.com/spotify/annoy/blob/8ceba5640cc1368d81a1f7ca1eea2f24b338dfbe/src/annoylib.h#L554-L567))  