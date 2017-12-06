# spotify-annoy-id-mapping

## Install

1. Please clone or download this repository
2. Get the [spotiy/annoy](https://github.com/spotify/annoy) python package from [PyPI](https://pypi.python.org/pypi/annoy)
- if you have [Python Package Index](https://pypi.python.org/pypi) installed, run `sudo pip install annoy`

## How it works

1. Build your annoy index with `BuildAnnoyIndex`  

- sample of an csv file for input, to build the index (it has to be in that form!) 
```
-0.22,  -0.45,  -0.37,  0.17,  0.28,  -0.38,  0.23,  -0.19,  0.16,  -0.29,  -0.24,  0.15,  0.34,  0.07,  0.12,  -0.12,  0.04,  -0.04,  0.06,  -0.07,  0.31,  0.02,  -0.06,  0.32,  -0.13,  0.26,  0.08,  -0.14,  -0.17,  -0.19,  -0.18,  0.29,  -0.00,  0.16,  -0.03,  -0.03,  0.33,  0.40,  -0.13,  -0.33,  -0.15,  -0.15,  -0.17,  -0.10,  -0.18,  0.19,  -0.14,  -0.09,  -0.16,  0.07,  0.10,  -0.27,  0.18,  -0.18,  -0.02,  0.30,  0.18,  0.14,  -0.05,  0.16,  -0.12,  -0.07,  0.05,  0.39,  -0.32,  0.09,  0.34,  0.66,  0.03,  0.16,  0.27,  0.18,  0.09,  -0.32,  -0.12,  0.43,  -0.19,  0.06,  -0.02,  0.04,  0.01,  -0.06,  0.07,  -0.08,  -0.13,  -0.07,  -0.20,  -0.07,  0.37,  -0.33,  0.37,  0.20,  0.07,  0.14,  0.08,  0.03,  -0.02,  -0.63,  0.01,  -0.40
0.49,  -0.74,  0.11,  0.30,  -0.41,  -0.41,  -0.10,  0.28,  0.03,  -0.35,  -0.15,  0.38,  0.08,  -0.22,  -0.10,  0.28,  -0.07,  0.28,  0.13,  1.16,  -0.71,  -0.90,  0.13,  0.04,  0.89,  0.37,  0.05,  -0.14,  0.13,  0.58,  -0.23,  0.29,  -0.64,  0.38,  0.03,  1.00,  -0.00,  0.39,  0.20,  0.27,  0.27,  0.56,  0.30,  1.02,  -0.29,  0.78,  -0.84,  0.29,  0.20,  0.29,  0.45,  0.58,  -0.12,  0.64,  -0.05,  -0.04,  -0.14,  -0.35,  -1.79,  0.22,  0.13,  0.13,  0.83,  -0.11,  0.58,  0.20,  0.46,  -0.38,  0.05,  0.33,  -0.04,  -0.26,  -0.66,  -0.38,  0.28,  0.42,  0.05,  0.17,  0.22,  0.09,  -0.37,  0.42,  -0.27,  0.09,  0.23,  -0.14,  -0.07,  -0.03,  -0.36,  -0.65,  -0.24,  0.24,  -0.48,  -0.45,  0.27,  -0.06,  0.59,  0.22,  -1.46,  0.05
0.08,  -0.37,  -0.63,  -0.58,  -0.44,  -0.74,  -0.35,  -0.02,  0.48,  -0.50,  -0.20,  0.26,  -0.59,  0.72,  0.15,  0.07,  0.52,  0.42,  0.67,  1.04,  -0.25,  0.17,  0.06,  0.14,  0.14,  0.40,  0.14,  0.28,  -0.47,  0.19,  -0.83,  -0.45,  0.44,  0.40,  -0.10,  -0.21,  0.89,  -0.38,  -0.09,  0.05,  -0.16,  0.65,  -0.09,  0.69,  -0.31,  -0.22,  -0.41,  0.79,  -0.70,  1.39,  0.40,  -0.48,  -0.55,  -0.23,  -0.22,  0.86,  0.25,  0.17,  -0.01,  -0.31,  -0.47,  -0.07,  -0.03,  -0.36,  0.50,  0.52,  0.03,  -0.85,  -0.27,  -0.17,  0.36,  0.42,  0.21,  0.45,  -0.03,  0.98,  -0.59,  0.39,  0.51,  0.18,  -0.40,  -0.89,  0.14,  0.23,  0.48,  0.08,  0.34,  -0.14,  -0.13,  1.09,  -0.58,  -0.16,  0.89,  -0.64,  0.28,  -0.10,  0.21,  -0.04,  -0.06,  0.31
```

2. Give the `indexpath` in `ÌdMapping` to the created annoy index for nearest neighbor search
2. How many search nodes do you want for `k` (see [*Tradeoffs*](https://github.com/spotify/annoy#tradeoffs))
3. Give a `n` for how many results you want to get back as the closest items

## Where is the loaded index stored? 

Annoy works with memory-mapped files (see [load()](https://github.com/spotify/annoy/blob/8ceba5640cc1368d81a1f7ca1eea2f24b338dfbe/src/annoylib.h#L554-L567)). So the resource has to be stored in a way, that the operating system can reference through a file descriptor. The correlation between the file and the memory space permits to treat the mapped portion as if it were primary memory. (see [Memory-mapped file](https://en.wikipedia.org/wiki/Memory-mapped_file))  