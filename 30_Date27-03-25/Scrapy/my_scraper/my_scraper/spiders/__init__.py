# This package will contain the spiders of your Scrapy project
#
# Please refer to the documentation for information on how to create and manage
# your spiders.
from pkgutil import iter_modules
from importlib import import_module

__all__ = []

for module in iter_modules(__path__):
    import_module(f"{__name__}.{module.name}")
    __all__.append(module.name)
