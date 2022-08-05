package com.rainarmy.tools.test;

import org.usb4java.*;

public class UsbProvider {

    public Device findDevice(short vendorId, short productId) {
        // Read the USB device list
        DeviceList list = new DeviceList();
        int result = LibUsb.getDeviceList(null, list);
        if (result < 0) throw new LibUsbException("Unable to get device list", result);

        try
        {
            // Iterate over all devices and scan for the right one
            for (Device device: list){
                DeviceDescriptor descriptor = new DeviceDescriptor();
                result = LibUsb.getDeviceDescriptor(device, descriptor);
                if (result != LibUsb.SUCCESS){
                    throw new LibUsbException("Unable to read device descriptor", result);
                }
                if (descriptor.idVendor() == vendorId && descriptor.idProduct() == productId){
                    return device;
                }
            }
        }
        finally
        {
            // Ensure the allocated device list is freed
            LibUsb.freeDeviceList(list, true);
        }

        // Device not found
        return null;
    }

    public static void main(String[] args) {
        Context context = new Context();
        int result = LibUsb.init(context);
        if (result != LibUsb.SUCCESS){
            throw new LibUsbException("Unable to initialize libusb.", result);
        }
        UsbProvider usbProvider = new UsbProvider();
        Device device = usbProvider.findDevice((short) 0, (short) 0);

        DeviceHandle handle = new DeviceHandle();
        int libUsbResult = LibUsb.open(device, handle);

        if (libUsbResult != LibUsb.SUCCESS){
            throw new LibUsbException("Unable to open USB device", libUsbResult);
        }
        try
        {
            // Use device handle here
//            int result = LibUsb.claimInterface(handle, interfaceNumber);
//            if (result != LibUsb.SUCCESS) throw new LibUsbException("Unable to claim interface", result);
//            try
//            {
//                // Use interface here
//            }
//            finally
//            {
//                result = LibUsb.releaseInterface(handle, interfaceNumber);
//                if (result != LibUsb.SUCCESS) throw new LibUsbException("Unable to release interface", result);
//            }
        }
        finally
        {
            LibUsb.close(handle);
        }
        LibUsb.exit(context);
    }

}




