# ALDE
ALDE, or "Android on Linux Desktop Environments", is an Android SDK layer for Linux
desktop environments. It has the capability of running Android apps/games natively
on Linux, which translates the native Bionic layer into glibc.

Now, it wouldn't be an Android port, when there is no possibility of running an
actual Android system. I will make that possibility that includes using the default
System image, which will do its jobs. No emulators / VMs involved, but within a
Window that will run a full Android system. Something that `wine` does its job at.

## Expected requirements
Now, I am not sure with the requirements, but these are my speculations on what the
requirements could be that the systems (mainly from the Android sources) say:

- At least 2 GB of RAM available
- AMD Ryzen 3 3200G CPU or equivalent
- At least 3.6 GB of Storage (SSD)

Recommended specifications might be:

- 4 GB or more RAM available (depending on how much RAM you want to spare for the
  system)
- AMD Ryzen 7 5700G CPU or equivalent
- 4.5 GB of Storage space (NVMe)

Longer description coming soon