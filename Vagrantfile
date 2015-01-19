Vagrant.configure(2) do |config|
  if Vagrant.has_plugin?("vagrant-cachier")
    config.cache.scope = :box
  end

  ## Vagrant vm configuration
  config.vm.box = "hashicorp/precise32"
  config.vm.network "private_network", ip: "1.0.0.7"
  config.vm.hostname = 'todos-app'

  ## Share folders between your host and vm
  # config.vm.synced_folder "./HOST_FOLDER", "/VM_FOLDER", create: true

  ## Inline script execution
  config.vm.provision "shell", run: "always", inline: <<-SHELL
    apt-get update
    wget -qO- https://gist.github.com/MartinsThiago/8a6782ad27d7232c23c3/raw/mongodb_precise.sh | sh
    wget -qO- https://gist.github.com/MartinsThiago/a7e55bbffb7391713388/raw/jdk8_precise.sh | sh
  SHELL

  # External script execution
  # config.vm.provision "shell", run: "always", path: "./<script.sh>"

end