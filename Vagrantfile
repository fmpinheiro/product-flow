# -*- mode: ruby -*-
# vi: set ft=ruby :

$script = <<SCRIPT
echo I am provisioning...
date > /etc/vagrant_provisioned_at
SCRIPT

Vagrant.configure("2") do |config|
  config.vm.provider "virtualbox" do |v|
    v.name = "pgproductflow"
  end
  config.vm.synced_folder ".", "/vagrant", :mount_options => ["dmode=777","fmode=777"]
  config.vm.provision "shell", inline: $script
end

Vagrant::Config.run do |config|
  config.vm.box = "precise64"
  config.vm.box_url = "http://files.vagrantup.com/precise64.box"

  config.vm.share_folder "bootstrap", "/mnt/bootstrap", ".", :create => true
  config.vm.provision :shell, :path => "Vagrant-provision.sh"
  
  # PostgreSQL Server port forwarding
  config.vm.forward_port 5432, 5432
end
